import {Component, Input, OnInit} from "@angular/core";
import {NgbModal, NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {LoggerService} from "eds-common-js";
import {PatchesService} from "../patches/patches.service";
import {Patch} from "../patches/models/Patch";

@Component({
    selector: 'ngbd-modal-content',
    template: require('./patchEditor.html')
})
export class PatchEditDialog implements OnInit {

    public static open(modalService: NgbModal, patch : Patch) {
        const modalRef = modalService.open(PatchEditDialog, { backdrop : "static", size : "lg"});
        modalRef.componentInstance.resultData = patch;

        return modalRef;
    }

    @Input() resultData;

    editMode : boolean = false;

    patchDescription: string;
    createdTimestamp: string;
    patchScript: string;

    constructor(protected $uibModalInstance : NgbActiveModal,
                private logger : LoggerService, private patchesService:PatchesService) {

    }

    ngOnInit(): void {

        var vm = this;

        this.initialiseEditMode(this.resultData);
     }

    initialiseEditMode(resultData : Patch) {
        var vm = this;

        vm.editMode = true;

        vm.patchDescription = resultData.patchDescription;
        vm.createdTimestamp = resultData.createdTimestamp.replace(" ","T");
        vm.patchScript = resultData.patchScript;
    }

    save() {
        var vm = this;

        if (!vm.patchDescription) {
            this.logger.error('Please enter a patch description');
            return;
        }

        vm.resultData.patchDescription = vm.patchDescription;
        vm.resultData.createdTimestamp = vm.createdTimestamp;
        vm.resultData.patchScript = vm.patchScript;

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
