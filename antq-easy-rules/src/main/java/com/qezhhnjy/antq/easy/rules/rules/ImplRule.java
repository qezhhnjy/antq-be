package com.qezhhnjy.antq.easy.rules.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

/**
 * @author zhaoyangfu
 * @since 2022/7/20-17:40
 */
public class ImplRule implements Rule {

    @Override
    public boolean evaluate(Facts facts) {
        return false;
    }

    @Override
    public void execute(Facts facts) throws Exception {

    }

    @Override
    public int compareTo(Rule o) {
        return 0;
    }
}
