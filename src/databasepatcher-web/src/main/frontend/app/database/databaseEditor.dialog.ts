import {Component, Input, OnInit} from "@angular/core";
import {NgbModal, NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {LoggerService} from "eds-common-js";
import {DashboardService} from "../dashboard/dashboard.service";
import {Database} from "../dashboard/models/Database";
import {Patch} from "../patches/models/Patch";
import {PatchesService} from "../patches/patches.service";

@Component({
    selector: 'ngbd-modal-content',
    template: require('./databaseEditor.html')
})
export class DatabaseEditDialog implements OnInit {

    public static open(modalService: NgbModal, database : Database) {
        const modalRef = modalService.open(DatabaseEditDialog, { backdrop : "static", size : "lg"});
        modalRef.componentInstance.resultData = database;

        return modalRef;
    }

    @Input() resultData;

    editMode : boolean = false;

    name: string;
    hostname: string;
    port: string;
    username: string;
    password: string;
    lastPatchTimestamp: string;
    lastStatus: string;
    currentVersion: string;
    nextPatchTimestamp: string;
    targetVersion: string;
    regionId: string;
    availabilityZoneId: string;
    dataTypeId: string;
    patchId: string;

    runNow: string;

    regions = <any>[];
    zones = <any>[];
    dataTypes = <any>[];

    patches = <any>[];


    statuses = [
        {id: 0, name: 'Failed'},
        {id: 1, name: 'Success'},
    ];

    constructor(protected $uibModalInstance : NgbActiveModal,
                private logger : LoggerService, private dashboardService:DashboardService, private patchesService:PatchesService) {

    }

    ngOnInit(): void {

        var vm = this;

        vm.dashboardService.getRegions()
            .subscribe(
                (data) => {
                    vm.regions = data;
                });
        vm.dashboardService.getZones()
            .subscribe(
                (data) => {
                    vm.zones = data;
                });
        vm.dashboardService.getDataTypes()
            .subscribe(
                (data) => {
                    vm.dataTypes = data;
                });

        vm.patchesService.getPatches()
            .subscribe(
                (data) => {
                    vm.patches = data;
                });

         this.initialiseEditMode(this.resultData);
     }

    initialiseEditMode(resultData : Database) {
        var vm = this;

        vm.editMode = true;

        vm.name = resultData.name;
        vm.hostname = resultData.hostname;
        vm.port = resultData.port;
        vm.username = resultData.username;
        vm.password = resultData.password;
        vm.lastPatchTimestamp = resultData.lastPatchTimestamp.replace(" ","T");
        vm.lastStatus = resultData.lastStatus;
        vm.currentVersion = resultData.currentVersion;
        vm.nextPatchTimestamp = resultData.nextPatchTimestamp.replace(" ","T");
        vm.targetVersion = resultData.targetVersion;
        vm.regionId = resultData.regionId;
        vm.availabilityZoneId = resultData.availabilityZoneId;
        vm.dataTypeId = resultData.dataTypeId;
        vm.patchId = resultData.patchId;
    }

    save() {
        var vm = this;

        if (!vm.name) {
            this.logger.error('Please enter a database name');
            return;
        }

        vm.resultData.name = vm.name;
        vm.resultData.hostname = vm.hostname;
        vm.resultData.port = vm.port;
        vm.resultData.username = vm.username;
        vm.resultData.password = vm.password;
        vm.resultData.lastPatchTimestamp = vm.lastPatchTimestamp;
        vm.resultData.lastStatus = vm.lastStatus;
        vm.resultData.currentVersion = vm.currentVersion;
        vm.resultData.nextPatchTimestamp = vm.nextPatchTimestamp;
        vm.resultData.targetVersion = vm.targetVersion;
        vm.resultData.regionId = vm.regionId;
        vm.resultData.availabilityZoneId = vm.availabilityZoneId;
        vm.resultData.dataTypeId = vm.dataTypeId;
        vm.resultData.patchId = vm.patchId;
        vm.resultData.runNow = vm.runNow;

        this.ok();
    }

    ok() {
        this.$uibModalInstance.close(this.resultData);
        console.log('OK Pressed');
    }

    cancel() {
        this.$uibModalInstance.dismiss('cancel');
        console.log('Cancel Pressed');
    }
}
