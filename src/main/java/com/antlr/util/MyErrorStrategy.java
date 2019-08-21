package com.antlr.util;

import org.antlr.v4.runtime.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 语法检查，错误策略处理
 *
 * @author prs
 * @Date 2019/08/21
 */
public class MyErrorStrategy extends DefaultErrorStrategy {
    //是否合法
    private boolean isValidated = true;
    private RecognitionException exception;
    private Parser errRecognizer;
    private static final Logger logger = LoggerFactory.getLogger(MyErrorStrategy.class);

    public MyErrorStrategy() {
        super();
    }

    @Override
    protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e) {
        super.reportFailedPredicate(recognizer, e);
        logger.warn("FailedPredicate ->" + recognizer.getContext().getText()
        );
        isValidated = false;
        errRecognizer = recognizer;
        exception = e;
    }

    @Override
    protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
        super.reportNoViableAlternative(recognizer, e);
        logger.warn("NoViableAlternative ->" + recognizer.getContext().getText()
        );
        isValidated = false;
        errRecognizer = recognizer;
        exception = e;
    }

    @Override
    protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
        super.reportInputMismatch(recognizer, e);
        logger.warn("mismatched context ->" + recognizer.getContext().getText()
        );
        isValidated = false;
        errRecognizer = recognizer;
        exception = e;
    }

    public boolean isValidated() {
        if (isValidated && exception != null && errRecognizer != null) {
            //不被识别的异常
            errRecognizer.notifyErrorListeners(exception.getOffendingToken(), exception.getMessage(), exception);
            logger.warn("unknown recognition error type:" + exception.getClass().getName());
        }
        return isValidated;
    }

}
