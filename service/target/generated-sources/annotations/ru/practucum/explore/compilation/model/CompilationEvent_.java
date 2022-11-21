package ru.practucum.explore.compilation.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.practucum.explore.events.model.Event;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CompilationEvent.class)
public abstract class CompilationEvent_ {

	public static volatile SingularAttribute<CompilationEvent, Compilation> compilation;
	public static volatile SingularAttribute<CompilationEvent, Integer> id;
	public static volatile SingularAttribute<CompilationEvent, Event> event;

	public static final String COMPILATION = "compilation";
	public static final String ID = "id";
	public static final String EVENT = "event";

}

