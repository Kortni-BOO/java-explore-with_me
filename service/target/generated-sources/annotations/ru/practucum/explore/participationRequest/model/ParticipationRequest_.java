package ru.practucum.explore.participationRequest.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.practucum.explore.events.model.Event;
import ru.practucum.explore.participationRequest.enums.Status;
import ru.practucum.explore.user.model.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParticipationRequest.class)
public abstract class ParticipationRequest_ {

	public static volatile SingularAttribute<ParticipationRequest, User> requester;
	public static volatile SingularAttribute<ParticipationRequest, LocalDateTime> created;
	public static volatile SingularAttribute<ParticipationRequest, Long> id;
	public static volatile SingularAttribute<ParticipationRequest, Event> event;
	public static volatile SingularAttribute<ParticipationRequest, Status> status;

	public static final String REQUESTER = "requester";
	public static final String CREATED = "created";
	public static final String ID = "id";
	public static final String EVENT = "event";
	public static final String STATUS = "status";

}

