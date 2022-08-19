package group.msg.at.cloud.cloudtrain;

/**
 * {@code Constants} for support of Micrometer's Metrics annotations.
 */
public interface MetricsConstants {

    /**
     * Name prefix for all metrics related to business operation invocations.
     */
    String BUSINESS_OPERATION_METRIC_NAME = "application_business_operation";

    /**
     * Extra metric tag specifying the business operation's name.
     */
    String BUSINESS_OPERATION_NAME_TAG = "operation";
}
