package com.dksvip.core.web.aspect;


import cn.hutool.core.exceptions.ValidateException;
import com.dksvip.common.error.AppErrors;
import com.dksvip.common.exception.AppException;
import com.dksvip.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author linjian
 * @date 2019/3/19
 */
@Slf4j
@Aspect
@Component
public class ControllerResultAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)" +
            "&& execution(com.dksvip.common.response.Result *.*(..))")
    public void controllerResult() {
    }

    @Around("controllerResult()")
    public Result<?> doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Result<?> result = new Result<>();
        try {
            result = (Result<?>) proceedingJoinPoint.proceed();
        } catch (AppException e) {
            log.error("业务异常 " + Arrays.toString(proceedingJoinPoint.getArgs()), e);
            result.setSuccess(false);
            result.setCode(e.getCode());
            result.setMessage(e.getMessage());
        } catch (IllegalArgumentException | ValidateException e) {
            result.setSuccess(false);
            result.setCode(AppErrors.PARAM_ERROR.getCode());
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("系统出错 " + proceedingJoinPoint.getSignature(), e);
            result.setSuccess(false);
            result.setCode(AppErrors.SYSTEM_ERROR.getCode());
            result.setMessage(e.getMessage());
        }
//        } catch (ConstraintViolationException e) {
//            result.setSuccess(false);
//            result.setCode(DemoErrors.PARAM_ERROR.getCode());
//            result.setMessage(e.getMessage().substring(e.getMessage().indexOf(":") + 2));
//        } catch (RuntimeException e) {
//            log.error("系统出错 " + Arrays.toString(proceedingJoinPoint.getArgs()), e);
//            result.setSuccess(false);
//            result.setCode(DemoErrors.SYSTEM_ERROR.getCode());
//            result.setMessage(DemoErrors.SYSTEM_ERROR.getMessage());
//        }
        return result;
    }
}
