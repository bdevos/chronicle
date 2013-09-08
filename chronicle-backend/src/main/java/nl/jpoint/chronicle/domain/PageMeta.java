package nl.jpoint.chronicle.domain;

import com.google.code.morphia.annotations.Embedded;

@Embedded
public class PageMeta {

    private long created;

    private long updated;

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }
}
