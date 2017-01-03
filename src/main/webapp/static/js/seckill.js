//存放主要交互逻辑的js代码
//javascript 模块化
var seckill = {
    //封装秒杀相关AJAX地址
    URL: {
        now: function () {
            return '/seckill/time/now'
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    //验证手机号
    vardataPhone: function (phone) {
        if (/^1[3|4|5|7|8]\d{9}$/.test(phone)) {
            // if(phone){
            return true;
        } else {
            return false
        }
    },
    //获取秒杀地址，控制显示逻辑，执行秒杀
    handlerSeckillkill: function (seckillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //在回调函数中，执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开启秒杀
                    node.show();
                    //获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log('killUrl=' + killUrl);
                    //只绑定一次点击事件
                    $('#killBtn').one('click', function () {
                        //执行秒杀操作
                        $(this).addClass('disablad');
                        //发送请求
                        $.post(killUrl, {}, function (result) {

                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //显示秒杀结果
                                node.html('<span class="label label-success">' + stateInfo + '</span>')
                            }else {
                                console.log(result);
                            }
                        });
                    });
                } else {
                    //未开启秒杀，客户机时间不同步
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    //重新进入计时逻辑
                    seckill.countdown(seckillId, now, start, end);
                }
            } else {
                console.log(result);
            }
        });

    },
    //时间验证
    countdown: function (seckillID, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束！');
        } else if (nowTime < startTime) {
            //秒杀未开始,计时
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //时间结束后回调事件
            }).on('finish.countdown', function () {
                //获取秒杀地址，控制现实逻辑，执行秒杀
                seckill.handlerSeckillkill(seckillID, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handlerSeckillkill(seckillID, seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证交互，计时交互
            //规划交互流程
            //在Cookie中查找手机号
            var killPhone = $.cookie('killPhone');

            //验证手机号
            if (!seckill.vardataPhone(killPhone)) {
                //验证为空，绑定手机号
                var killPhoneModal = $('#killPhoneModal');
                //显示弹出层
                killPhoneModal.modal({
                    show: true,
                    backdrop: 'static',//禁止位置关闭
                    keybrard: false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.vardataPhone(inputPhone)) {
                        //电话写入cookie 设置时间为7天，在'/seckill'下有效
                        $.cookie('killPhone', inputPhone, {
                            expires: 7, path: '/seckill'
                        });
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号输入错误</label>').show(300);
                    }
                });
            } else {
                //已经登录
                //计时交互
                var seckillId = params['seckillId'];
                var startTime = params['startTime'];
                var endTime = params['endTime'];
                $.get(seckill.URL.now(), {}, function (result) {
                    if (result && result['success']) {
                        var nowTime = result['data'];
                        //时间判断
                        seckill.countdown(seckillId, nowTime, startTime, endTime)
                    } else {
                        console.log('result=' + result)
                    }
                })
            }
        }
    }
};
