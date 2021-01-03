package com.suleymangunduz.casestudyapp.util.enums;

import com.suleymangunduz.casestudyapp.R;

public enum ChartTypeEnum {
    PLATFORM_DISTRIBUTION(1, R.string.platform_distribution),
    BROWSER_DISTRIBUTION(2, R.string.browser_distribution),
    COUNTRY_DISTRIBUTION(3, R.string.country_distribution),
    AVERAGE_RATINGS_PER_PLATFORM(4, R.string.average_ratings_per_platform),
    AVERAGE_RATINGS_PER_BROWSER(5, R.string.average_ratings_per_browser),
    AVERAGE_RATINGS_PER_COUNTRY(6, R.string.average_ratings_per_country);

    private final Integer id;
    private final Integer nameResourceId;

    ChartTypeEnum(Integer id, Integer nameResourceId) {
        this.id = id;
        this.nameResourceId = nameResourceId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNameResourceId() {
        return nameResourceId;
    }

    public static ChartTypeEnum fromGraphTypeEnumId(Integer id) {
        for (ChartTypeEnum type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }
}
