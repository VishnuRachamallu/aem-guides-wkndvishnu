package com.adobe.aem.guides.vishnu.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "B Scheduler config",description = "Vishnu wknd scheduler config")

public @interface SchedulerConfiguration {

	@AttributeDefinition(name = "Cron Expression",description = "Cron Expression used by Scheduler",type = AttributeType.STRING)
	public String cornExpression() default "*/60 * * * * ?";
	
	@AttributeDefinition(name="Scheduler Name",description = "Name of Scheduler",type = AttributeType.STRING)
	public String schedulerName() default "Vishnu Custom Sling Scheduler config";
	
}
