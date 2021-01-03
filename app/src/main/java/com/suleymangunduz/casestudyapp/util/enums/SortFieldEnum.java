package com.suleymangunduz.casestudyapp.util.enums;

import com.suleymangunduz.casestudyapp.R;

public enum SortFieldEnum {
    RATING(1, R.string.sort_by_rating),
    VERSION(2, R.string.sort_by_version),
    PLATFORM(3, R.string.sort_by_platform);

    private final Integer id;
    private final Integer nameResourceId;

    SortFieldEnum(Integer id, Integer nameResourceId) {
        this.id = id;
        this.nameResourceId = nameResourceId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNameResourceId() {
        return nameResourceId;
    }

    public static SortFieldEnum fromSortFieldEnumId(Integer id) {
        for (SortFieldEnum type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }
}
