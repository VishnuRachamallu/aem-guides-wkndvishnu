package com.adobe.aem.guides.vishnu.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "config3", description = "config3 description")
public @interface SchedulerConfiguration3 {

	@AttributeDefinition(name = "This is cron expression", description = "this is description", type = AttributeType.STRING)
	public String crexp() default "*/30 * * * * ?";

	@AttributeDefinition(name = "Scheduler Name", description = "Name of Scheduler", type = AttributeType.STRING)
	public String schedulerName() default "Vishnu Custom Sling Scheduler config3";
}
