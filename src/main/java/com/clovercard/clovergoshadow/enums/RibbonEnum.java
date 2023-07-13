package com.clovercard.clovergoshadow.enums;

public enum RibbonEnum {
    SHADOW_RIBBON("clovergoshadow_shadowribbon"),
    PURIFIED_RIBBON("clovergoshadow_purifiedribbon"),
    APEX_SHADOW_RIBBON("clovergoshadow_apexribbon"),
    APEX_PURIFIED_RIBBON("clovergoshadow_apexribbon2");
    private String ribbonId;
    RibbonEnum(String ribbonId) {
        this.ribbonId = ribbonId;
    }
    public String getRibbonId() {
        return this.ribbonId;
    }
}
