package ru.practicum.statistic.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EndpointHit.class)
public abstract class EndpointHit_ {

	public static volatile SingularAttribute<EndpointHit, String> app;
	public static volatile SingularAttribute<EndpointHit, String> ip;
	public static volatile SingularAttribute<EndpointHit, Long> id;
	public static volatile SingularAttribute<EndpointHit, String> uri;
	public static volatile SingularAttribute<EndpointHit, LocalDateTime> timestamp;

	public static final String APP = "app";
	public static final String IP = "ip";
	public static final String ID = "id";
	public static final String URI = "uri";
	public static final String TIMESTAMP = "timestamp";

}

