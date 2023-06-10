package group.msg.at.cloud.cloudtrain.core.boundary;

import group.msg.at.cloud.cloudtrain.adapter.persistence.jpa.repository.GenericRepository;
import group.msg.at.cloud.cloudtrain.core.control.UserPermissionVerifier;
import group.msg.at.cloud.cloudtrain.core.entity.Task;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static group.msg.at.cloud.cloudtrain.MetricsConstants.BUSINESS_OPERATION_METRIC_NAME;
import static group.msg.at.cloud.cloudtrain.MetricsConstants.BUSINESS_OPERATION_NAME_TAG;

/**
 * Simple {@code Boundary} that manages {@code Task} entities.
 */
@Dependent
@Transactional(Transactional.TxType.REQUIRED)
@RolesAllowed("CLOUDTRAIN_USER")
public class TaskManagement {

    @Inject
    GenericRepository repository;

    @Inject
    UserPermissionVerifier verifier;

    @Counted(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_add"})
    @Timed(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_add"})
    @NotNull
    public UUID addTask(@NotNull @Valid Task newTask) {
        verifier.requirePermission("TASK_CREATE");
        newTask.setId(UUID.randomUUID());
        this.repository.addEntity(newTask);
        return newTask.getId();
    }

    @Counted(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_modify"})
    @Timed(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_modify"})
    public void modifyTask(@NotNull @Valid Task modifiedTask) {
        verifier.requirePermission("TASK_UPDATE");
        this.repository.setEntity(modifiedTask);
    }

    @Counted(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_get"})
    @Timed(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_get"})
    public Task getTaskById(@NotNull UUID taskId) {
        verifier.requirePermission("TASK_READ");
        return this.repository.getEntityById(Task.class, taskId);
    }

    @Counted(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_delete"})
    @Timed(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_delete"})
    public void removeTask(@NotNull UUID taskId) {
        verifier.requirePermission("TASK_DELETE");
        this.repository.removeEntityById(Task.class, taskId);
    }

    @Counted(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_browse"})
    @Timed(value = BUSINESS_OPERATION_METRIC_NAME, extraTags = {BUSINESS_OPERATION_NAME_TAG, "task_browse"})
    @NotNull
    public List<Task> getAllTasks() {
        verifier.requirePermission("TASK_READ");
        return this.repository.queryEntities(Task.class, Task.QUERY_ALL, null);
    }
}
