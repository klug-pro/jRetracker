package org.jretracker;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail Filippov
 * Date: 5/27/12
 * Time: 2:17 PM
 */
class AnnounceValidator {

    private boolean valid;
    private String error;

    public AnnounceValidator(Map<String, String> params) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Request not content params: ");
        valid = true;

        if (!params.containsKey("info_hash")) {
            valid = false;
            buffer.append("info_hash not exists.\n");
        }

        if (!params.containsKey("port")) {
            valid = false;
            buffer.append("port not exists.\n");
        } else {
            if (isNumber(params.get("port"))) {
                valid = false;
                buffer.append("port is not numeric.\n");
            }
        }
        if (!params.containsKey("peer_id")) {
            valid = false;
            buffer.append("peer_id not exists.\n");
        }

        if (!params.containsKey("downloaded")) {
            valid = false;
            buffer.append("downloaded not exists.\n");
        } else {
            if (isNumber(params.get("downloaded"))) {
                valid = false;
                buffer.append("downloaded is not numeric.\n");
            }
        }

        if (!params.containsKey("uploaded")) {
            valid = false;
            buffer.append("uploaded not exists.\n");
        } else {
            if (isNumber(params.get("uploaded"))) {
                valid = false;
                buffer.append("uploaded is not numeric.\n");
            }
        }

        if (!params.containsKey("left")) {
            valid = false;
            buffer.append("left not exists.\n");
        } else {
            if (isNumber(params.get("left"))) {
                valid = false;
                buffer.append("left is not numeric.\n");
            }
        }

        if(!params.containsKey("event")) {
            valid = false;
            buffer.append("event not exists.\n");
        } else {
            String event = params.get("event");
            if (!event.equals("started")
                    && !event.equals("completed")
                    && !event.equals("stopped")) {
                valid = false;
                buffer.append("invalid event.\n");
            }
        }

        if (!valid) {
            error = buffer.toString();
        }
    }

    private boolean isNumber(String str) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(str);
        return !m.matches();
    }

    public boolean isValid() {
        return valid;
    }

    public String getError() {
        return error;
    }
}
