package net.pureessence.aspect;

import org.apache.commons.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class WebServiceAspect {
    private Log log;

    @Around("execution(* net.pureessence.component.WebServiceCaller.getJobStatus())")
    public String logWebServiceCall(ProceedingJoinPoint pjp) {
        String result = null;
        try {
            result = (String)pjp.proceed();
        } catch(Throwable t) {
            log.error("web service get job status failed", t);
        }
        return result;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
