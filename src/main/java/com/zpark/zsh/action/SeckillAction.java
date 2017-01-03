package com.zpark.zsh.action;

import com.zpark.zsh.dto.Exposer;
import com.zpark.zsh.dto.SeckillExecution;
import com.zpark.zsh.dto.SeckillResult;
import com.zpark.zsh.entity.Seckill;
import com.zpark.zsh.enums.SeckillStateEnum;
import com.zpark.zsh.exception.SeckillCloseException;
import com.zpark.zsh.exception.SeckillException;
import com.zpark.zsh.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取列表页
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping("/toList")
    public String toList(Model model){
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Integer seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "redirect:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody   //将响应封装为json数据
    public SeckillResult<Exposer> exposer(@PathVariable Integer seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }


    /**
     * 执行秒杀
     * @param seckillId
     * @param md5
     * @param killPhone
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(
            @PathVariable Integer seckillId,
            @PathVariable String md5,
            @CookieValue(value="killPhone",required = false) String killPhone) {
        SeckillResult<SeckillExecution> result;

        if (killPhone==null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
            //这是一种方法，还可以使用SpringMVC的验证信息
        }
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, killPhone, md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            return new SeckillResult<SeckillExecution>(true,execution);
        } catch (SeckillException e) {
            logger.error(e.getMessage(), e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true, execution);
        }

    }

    /**
     * 获取系统当前时间
     * @return
     */
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Long time = new Date().getTime();
        return new SeckillResult<Long>(true,time);
    }
}
