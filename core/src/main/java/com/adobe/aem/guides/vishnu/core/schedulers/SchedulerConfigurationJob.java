package com.adobe.aem.guides.vishnu.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "B Scheduler JOB config", description = "Vishnu wknd scheduler JOB config")

public @interface SchedulerConfigurationJob {
	@AttributeDefinition(name = "Cron Expression", description = "Cron Expression used by Scheduler", type = AttributeType.STRING)
	public String cornExpression() default "*/30 * * * * ?";

	@AttributeDefinition(name = "Scheduler Name", description = "Name of Scheduler", type = AttributeType.STRING)
	public String schedulerName() default "Vishnu Custom Sling Scheduler config";

}
