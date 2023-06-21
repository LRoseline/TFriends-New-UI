package com.tfriends.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BBCodeFilter {
    public String bbFilter(String content) {
        content = xssFilter(content);
        content = startFilter(content);
        content = tableFilter(content);

        return content;
    }

    public String xssFilter(String content) {
        content = content.replaceAll("&", "&amp;");
        content = content.replaceAll("\"", "&quot;");
        content = content.replaceAll("'", "&apos;");
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
        content = content.replaceAll("\\n", "<br>\n");

        return content;
    }

    public String startFilter(String content) {
        // Text Settings
        content = content.replaceAll("\\[b\\](.+?)\\[/b\\]", "<b>$1</b>");
        content = content.replaceAll("\\[i\\](.+?)\\[/i\\]", "<i>$1</i>");
        content = content.replaceAll("\\[u\\](.+?)\\[/u\\]", "<u>$1</u>");
        content = content.replaceAll("\\[s\\](.+?)\\[/s\\]", "<s>$1</s>");
        content = content.replaceAll("\\[sub\\](.+?)\\[/sub\\]", "<sub>$1</sub>");
        content = content.replaceAll("\\[sup\\](.+?)\\[/sup\\]", "<sup>$1</sup>");
        content = content.replaceAll("\\[font=(.+?)\\](.+?)\\[/font\\]", "<font face=\"$1\">$2</font>");
        content = content.replaceAll("\\[size=(.+?)\\](.+?)\\[/size\\]", "<font size=$1>$2</font>");
        content = content.replaceAll("\\[color=(.+?)\\](.+?)\\[/color\\]", "<font color=\"$1\">$2</font>");
        content = content.replaceAll("\\[url=(.+?)\\](.+?)\\[/url\\]", "<a href=\"$1\" target=\"_blank\">$2</a>");
        content = content.replaceAll("\\[url\\]$1\\[/url\\]", "<a href=\"$1\" target=\"_blank\">$1</a>");

        // Align Settings
        content = content.replaceAll("\\[left\\](.+?)\\[/left\\]", "<div align=\"left\">$1</div>");
        content = content.replaceAll("\\[center\\](.+?)\\[/center\\]", "<div align=\"center\">$1</div>");
        content = content.replaceAll("\\[right\\](.+?)\\[/right\\]", "<div align=\"right\">$1</div>");
        content = content.replaceAll("\\[justify\\](.+?)\\[/justify\\]", "<div align=\"justify\">$1</div>");

        content = content.replaceAll("\\[ltr\\](.+?)\\[/rtl\\]", "<div style=\"direction: ltr;\">$1</div>");
        content = content.replaceAll("\\[rtl\\](.+?)\\[/rtl\\]", "<div style=\"direction: rtl;\">$1</div>");

        content = content.replaceAll("</div>\\s*<br>", "</div>");

        // List Settings
        content = content.replaceAll("\\[li\\]", "<li>");
        content = this.behindBr(content, "/li");
        content = this.ulolFilter(content, "ul");
        content = this.ulolFilter(content, "ol");

        // Image Settings
        content = content.replaceAll("\\[img\\](.+?)\\[/img\\]", "<img src=\"$1\" />");
        content = content.replaceAll("\\[img=(.+?)x(.+?)\\](.+?)\\[/img\\]",
                "<img src=\"$3\" style=\"width: $1px;\" />");
        content = content.replaceAll("<img (.+?)>\\s*<br>\\s*<br>", "<img $1><br>");

        // Block Settings
        content = content.replaceAll("\\[youtube\\](.+?)\\[/youtube\\]",
                "<iframe class=\"yt-article\" src=\"https://www.youtube.com/embed/$1\" allowfullscreen></iframe>");
        content = content.replaceAll("\\[quote\\]", "<blockquote>");
        content = content.replaceAll("\\[/quote\\]\\s*<br>|\\[/quote\\]", "</blockquote>");
        content = content.replaceAll("\\[code\\]", "<code>");
        content = this.behindBr(content, "/code");
        content = this.behindBr(content, "hr");

        return content + "\n";
    }

    public String behindBr(String target, String tag) {
        target = target.replaceAll("\\[" + tag + "\\]\\s*<br>|\\[" + tag + "\\]", "<" + tag + ">");

        return target;
    }

    public String tableFilter(String content) {
        content = content.replaceAll("\\[/table\\]\\s*<br>", "\\[/table\\]");

        Pattern table = Pattern.compile("\\[table\\](.+?)\\[/table\\]", Pattern.DOTALL);
        Matcher matcher = table.matcher(content);

        while (matcher.find()) {
            String group = matcher.group(1);
            group = group.replace("<br>", "");

            content = content.replace(matcher.group(), "<table class=\"arc-table\">" + group + "</table>");
        }

        content = content.replaceAll("\\[tr\\]", "<tr>");
        content = content.replaceAll("\\[td\\](.+?)\\[/td\\]", "<td>$1</td>");
        content = content.replaceAll("\\[/tr\\]", "</tr>");

        return content;
    }

    public String ulolFilter(String content, String type) {
        // Elements
        String htmlStart = "<" + type + ">";

        // Br, Behind
        content = this.behindBr(content, type);
        content = this.behindBr(content, "/" + type);

        // Br, Front
        content = content.replaceAll("\\s*<br>\\s*" + htmlStart, "\n" + htmlStart);

        return content;
    }
}
