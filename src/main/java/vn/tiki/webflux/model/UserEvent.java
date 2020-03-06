package vn.tiki.webflux.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEvent {
    private String value;
    private String msg;
}
