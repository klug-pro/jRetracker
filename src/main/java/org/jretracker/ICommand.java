package org.jretracker;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/26/12
 * Time: 1:49 AM
 */
public interface ICommand {
    public String execute(Map<String, String> params);
}
