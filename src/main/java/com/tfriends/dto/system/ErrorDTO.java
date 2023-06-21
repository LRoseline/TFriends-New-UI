package com.tfriends.dto.system;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private int cod;
    private String mes;

    public String setMes() {
        return this.mes;
    }

    public String getMes() {
        mes = "OK";

        switch (cod) {
            case 100:
                mes = "Continue";
                break;
            case 101:
                mes = "Switching protocols";
                break;
            case 102:
                mes = "Processing";
                break;
            case 103:
                mes = "Early hints";
                break;
            case 200:
                mes = "OK";
                break;
            case 201:
                mes = "Created";
                break;
            case 202:
                mes = "Accepted";
                break;
            case 203:
                mes = "Non-Authoritative information";
                break;
            case 204:
                mes = "No content";
                break;
            case 206:
                mes = "Partial content";
                break;
            case 207:
                mes = "Multi status";
                break;
            case 300:
                mes = "Multiple choices";
                break;
            case 301:
                mes = "Moved permanently";
                break;
            case 302:
                mes = "Found";
                break;
            case 303:
                mes = "See other";
                break;
            case 304:
                mes = "Not modified";
                break;
            case 305:
                mes = "Use proxy";
                break;
            case 307:
                mes = "Temporary redirect";
                break;
            case 308:
                mes = "Permanent redirect";
                break;
            case 400:
                mes = "Bad request";
                break;
            case 401:
                mes = "Unauthorized";
                break;
            case 402:
                mes = "Payment required";
                break;
            case 403:
                mes = "Forbidden";
                break;
            case 404:
                mes = "Not found";
                break;
            case 405:
                mes = "Method not allowed";
                break;
            case 406:
                mes = "Not acceptable";
                break;
            case 407:
                mes = "Proxy authentication required";
                break;
            case 408:
                mes = "Request timeout";
                break;
            case 409:
                mes = "conflict";
                break;
            case 410:
                mes = "Gone";
                break;
            case 411:
                mes = "Length required";
                break;
            case 412:
                mes = "Precondition failed";
                break;
            case 413:
                mes = "Payload too large";
                break;
            case 414:
                mes = "Request-URI too long";
                break;
            case 415:
                mes = "Unsupported media type";
                break;
            case 416:
                mes = "Request range not satisfiable";
                break;
            case 417:
                mes = "Expectation failed";
                break;
            case 418:
                mes = "teapot";
                break;
            case 420:
                mes = "Enhance your calm";
                break;
            case 421:
                mes = "Misdirected request";
                break;
            case 422:
                mes = "Unprocessable entity";
                break;
            case 423:
                mes = "Locked";
                break;
            case 424:
                mes = "Failed dependency";
                break;
            case 425:
                mes = "Too early";
                break;
            case 426:
                mes = "Upgrade required";
                break;
            case 429:
                mes = "Too many requests";
                break;
            case 431:
                mes = "Request header fields too large";
                break;
            case 444:
                mes = "No response";
                break;
            case 450:
                mes = "Blocked by windows parental controls";
                break;
            case 451:
                mes = "Unavaliable for legal reasons";
                break;
            case 497:
                mes = "HTTP request sent to https port";
                break;
            case 498:
                mes = "Token expired or invalid";
                break;
            case 499:
                mes = "Client closed request";
                break;
            case 500:
                mes = "Internal server error";
                break;
            case 501:
                mes = "Not implemented";
                break;
            case 502:
                mes = "Bad gateway";
                break;
            case 503:
                mes = "Service unavaliable";
                break;
            case 504:
                mes = "Gateway timeout";
                break;
            case 506:
                mes = "Variant also negotiates";
                break;
            case 507:
                mes = "Insufficient storage";
                break;
            case 508:
                mes = "Loop detected";
                break;
            case 509:
                mes = "Bandwidth limit exceeded";
                break;
            case 510:
                mes = "Not extended";
                break;
            case 511:
                mes = "Network authentication required";
                break;
            case 521:
                mes = "Web server is down";
                break;
            case 522:
                mes = "Connection timed out";
                break;
            case 523:
                mes = "Origin is Unreachable";
                break;
            case 525:
                mes = "SSL handshake failed";
                break;
            case 599:
                mes = "Network connect timeout error";
                break;
            case 0:
                mes = "ERROR!";
            default:
                mes = "Other problem. We can not find it.";
                break;
        }
        return mes;
    }
}
