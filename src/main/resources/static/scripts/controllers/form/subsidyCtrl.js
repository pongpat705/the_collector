
'use strict';
angular
	.module('app')
		.controller('subsidyCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function subsidyCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.formName = $rootScope.moneyControlForms.SUBSIDY.value1;
		$scope.getAllNbutGet();
	});
	
	var paginationOptions = {
	        pageNumber: 0,
	        pageSize: 20,
	        sort: 'entryDate',
	        sortDirectColumn : 'entryDate.dir',
	        direction : 'desc',
	        link:_CONTEXT+'/api/moneyControls/search/findByControlType',
	        controlType : 'SUBSIDY'
    };
	
	$scope.paginationOptions = paginationOptions;
	
	$scope.moneyControlList = [];
	$scope.moneyControl = {};
	
//	$scope.patchPatient = function(patient, link){
//	patientServices.patchPatientParent(patient, link).then(function(response){
//		toastr.success('saved');
//	}).catch(function(response) {
//		console.error('Error',response);
//		toastr.error(response.data.message, 'Error');
//    });
//};
	
	$scope.deleteFromList = function(index){
		$scope.moneyControlList.splice(index, 1);
	};
	
	$scope.addToList = function(moneyControl){
		$scope.moneyControlList.push(moneyControl);
		$scope.moneyControl = {};
	};
	
	
	$scope.save = function(){
		formService.saveMoneyControl(paginationOptions.controlType, $scope.moneyControlList).then(function(response){
			toastr.success('saved');
			$scope.moneyControlList = [];
			$scope.getAllNbutGet();
		}).catch(function(response) {
 			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
 	});
	};
	
	$scope.getAllNbutGet = function(){
		var param = {'page':paginationOptions.pageNumber, 'size':paginationOptions.pageSize, 'sort':paginationOptions.sort, 'entryDate.dir':paginationOptions.direction,  'controlType':paginationOptions.controlType};
	     
	    formService.genericGetSort(paginationOptions.link, param).then(function(response){
				$scope.nbudgetGridOptions.data = response.data._embedded.moneyControls;
				$scope.nbudgetGridOptions.totalItems = response.data.page.totalElements;
	 	}).catch(function(response) {
	 			console.error('Error',response);
	 			toastr.error(response.data.message, 'Error');
	 	});
	};
	
	$scope.nbudgetGridOptions = {
            paginationPageSizes: [5, 10, 20],
            paginationPageSize: paginationOptions.pageSize,
            enableColumnMenus:false,
            enableAutoFitColumns: true,
            useExternalPagination: true,
            columnDefs: [
                {name:'วันที่', field : 'entryDate', width: 100, enableSorting: true},
                {name:'หมายเลขรายการ', field : 'transCode'},
    			{name:'เอกสารเลขที่', field : 'docNo'},
    			{name:'รายการ', field : 'description'},
    			{name:'รายรับ',
    				cellTemplate : 	'<div class="ui-grid-cell-contents">' +
    									'{{row.entity.cashIn | currency}}'+
    								'</div>'
				},
				{name:'รายรับ',
						cellTemplate : 	'<div class="ui-grid-cell-contents">' +
											'{{row.entity.cashOut | currency}}'+
										'</div>'
				},
				{name:'คงเหลือ',
						cellTemplate : 	'<div class="ui-grid-cell-contents">' +
											'{{row.entity.balance | currency}}'+
										'</div>'
				},
				{name:'ลูกจ้างชั่วคราว',
					cellTemplate : 	'<div class="ui-grid-cell-contents">' +
										'{{row.entity.temporary | currency}}'+
									'</div>'
				},
				{name:'ค่าตอบแทน',
					cellTemplate : 	'<div class="ui-grid-cell-contents">' +
										'{{row.entity.compensation | currency}}'+
									'</div>'
				},
				{name:'ค่าใช้สอย',
					cellTemplate : 	'<div class="ui-grid-cell-contents">' +
										'{{row.entity.usability | currency}}'+
									'</div>'
				},
				{name:'ค่าสาธารณูปโภค',
					cellTemplate : 	'<div class="ui-grid-cell-contents">' +
										'{{row.entity.utility | currency}}'+
									'</div>'
				},
				{name:'ค่าวัสดุ',
					cellTemplate : 	'<div class="ui-grid-cell-contents">' +
										'{{row.entity.material | currency}}'+
									'</div>'
				},
				{name:'ค่าครภัณฑ์',
					cellTemplate : 	'<div class="ui-grid-cell-contents">' +
										'{{row.entity.durable | currency}}'+
									'</div>'
				},
				{name:'ค่าที่ดินสิ่งก่อสร้าง',
					cellTemplate : 	'<div class="ui-grid-cell-contents">' +
										'{{row.entity.landBuild | currency}}'+
									'</div>'
				},
				{name:'เงินอุดหนุน',
					cellTemplate : 	'<div class="ui-grid-cell-contents">' +
										'{{row.entity.subsidy | currency}}'+
									'</div>'
				},
    			{name:'หมายเหตุ', field : 'remark'}
//    			 {
//                    name : 'Edit',
//                    cellTemplate : '<div class="ui-grid-cell-contents">' +
//                                        '<button class="btn btn-xs btn-info" title="Edit this" ng-click="grid.appScope.patchUser(row.entity, $event);" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit</button>' +
//                                   '</div>',
//                                   width: 100,
//                    enableCellEdit : false
////                    ,visible : isRoleAdmin
//                },
//                {
//                    name : 'Delete',
//                    cellTemplate : '<div class="ui-grid-cell-contents">' +
//                                        '<button class="btn btn-xs btn-danger" title="delete this" ng-click="grid.appScope.del(row.entity, $event);" ><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</button>' +
//                                   '</div>',
//                                   width: 100,
//                    enableCellEdit : false
////                    ,visible : isRoleAdmin
//                }
    			
            ],
            onRegisterApi: function(gridApi) {
               $scope.gridApi = gridApi;
               gridApi.pagination.on.paginationChanged(
                 $scope, 
                 function (newPage, pageSize) {
                   paginationOptions.pageNumber = newPage;
                   paginationOptions.pageSize = pageSize;
                   
                   var param = {'page':newPage-1, 'size':pageSize, 'sort':paginationOptions.sort, 'entryDate.dir':paginationOptions.direction,  'controlType':paginationOptions.controlType};
                   formService.genericGetSort(paginationOptions.link, param).then(function(response){
           			$scope.nbudgetGridOptions.data = response.data._embedded.moneyControls;
           			$scope.nbudgetGridOptions.totalItems = response.data.page.totalElements;
    	       		}).catch(function(response) {
    	       			console.error('Error',response);
    	       			toastr.error(response.data.message, 'Error');
    	       	    });
                });
            }
        };
	
	
  }
]);