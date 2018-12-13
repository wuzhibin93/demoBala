package com.enlink.src.main.java.com.enlink.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "panel_chart_mapping")
public class PanelChartMapping extends BaseModel {
    private static final long serialVersionUID = 1L;

    @Column(name = "panel_id")
    private Integer panelId;
    @Column(name = "chart_id")
    private Integer chartId;
}
