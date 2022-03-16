package com.jwzp_kr_kj.core;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public record OpeningHours(@Column(name = "\"from\"") String from, @Column(name = "\"to\"") String to) { }
