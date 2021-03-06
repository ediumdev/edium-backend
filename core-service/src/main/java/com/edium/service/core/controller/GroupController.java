package com.edium.service.core.controller;

import com.edium.library.payload.ApiResponse;
import com.edium.library.payload.PagedResponse;
import com.edium.library.util.AppConstants;
import com.edium.service.core.model.Group;
import com.edium.service.core.model.Organization;
import com.edium.service.core.payload.GroupRequest;
import com.edium.service.core.service.GroupService;
import com.edium.service.core.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    private final GroupService groupService;
    private final OrganizationService organizationService;

    @Autowired
    public GroupController(GroupService groupService, OrganizationService organizationService) {
        this.groupService = groupService;
        this.organizationService = organizationService;
    }

    @GetMapping("")
    public PagedResponse<Group> getAllGroup(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return groupService.findAll(page, size);
    }

    @PostMapping("")
    public Group createGroup(@Valid @RequestBody GroupRequest details) throws Exception {
        Group group = new Group();

        Organization organization = organizationService.findById(details.getOrganizationId());

        mapGroupRequestToModel(details, group, organization);

        return groupService.save(group);
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable(value = "id") Long id) {
        return groupService.findById(id);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable(value = "id") Long id,
                             @Valid @RequestBody GroupRequest details) {

        Group group = groupService.findById(id);

        Organization organization = organizationService.findById(details.getOrganizationId());

        mapGroupRequestToModel(details, group, organization);

        return groupService.update(group);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteGroup(@PathVariable(value = "id") Long id) {
        groupService.deleteById(id);

        return new ApiResponse(true, "success");
    }

    @GetMapping("/{id}/parent")
    public Group getParentGroup(@PathVariable(value = "id") Long groupId) {
        return groupService.getParentOfGroup(groupId);
    }

    @GetMapping("/{id}/children")
    public List<Group> getChildren(@PathVariable(value = "id") Long groupId) {
        return groupService.getAllChildrenGroups(groupId);
    }

    @GetMapping("/{id}/tree")
    public List<Group> getTree(@PathVariable(value = "id") Long groupId) {
        return groupService.getTreeGroupByGroupId(groupId);
    }

    private void mapGroupRequestToModel(GroupRequest details, Group group, Organization organization) {
        group.setName(details.getName());
        group.setOrganization(organization);
        group.setParentId(details.getParentId());
    }
}
