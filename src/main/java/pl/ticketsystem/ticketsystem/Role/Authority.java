package pl.ticketsystem.ticketsystem.Role;

public enum Authority {
    /*Klient:
    EVENT_SHOW("event_show")
    TICKET_ADD("ticket_add")
    TICKET_SHOW("ticket_show")
    ACCOUNT_SHOW_SINGLE("account_show_single")
    ACCOUNT_UPDATE_USER("account_update_user")

    Moderator:
    EVENT_ADD("event_add")
    EVENT_SHOW("event_show")
    EVENT_DELETE("event_delete")
    EVENT_ACCEPT("event_accept")
    EVENT_UPDATE("event_update")
    ACCOUNT_UPDATE_MODERATOR("account_update_moderator")
    ACCOUNT_SHOW_SINGLE("account_show_single")
    ACCOUNT_SHOW_MANY("account_show_many")
    ACCOUNT_DELETE("account_delete")
    PAYMENT_SHOW_MANY("payment_show_many")
    PAYMENT_UPDATE("payment_update")
    PAYMENT_DELETE("payment_delete")
    TICKET_ADD("ticket_add")
    TICKET_SHOW("ticket_show")

    Agencja:
    EVENT_SHOW("event_show")
    EVENT_ADD("event_add")
    EVENT_UPDATE("event_update")*/

    ACCOUNT_SHOW_SINGLE("account_show_single"),
    ACCOUNT_SHOW_MANY("account_show_many"),
    ACCOUNT_UPDATE_USER("account_update_user"),
    ACCOUNT_UPDATE_MODERATOR("account_update_moderator"),
    ACCOUNT_DELETE("account_delete"),
    EVENT_SHOW("event_show"),
    EVENT_DELETE("event_delete"),
    EVENT_ACCEPT("event_accept"),
    EVENT_UPDATE("event_update"),
    EVENT_ADD("event_add"),
    TICKET_ADD("ticket_add"),
    TICKET_SHOW("ticket_show"),
    PAYMENT_SHOW_MANY("payment_show_many"),
    PAYMENT_UPDATE("payment_update"),
    PAYMENT_DELETE("payment_delete");

    private final String authorityName;

    Authority(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityName() {
        return authorityName;
    }
}
