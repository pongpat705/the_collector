
'use strict';
angular
	.module('app')
		.controller('cashbookCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function cashbookCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.getAllCashBook();
	});
	
	$scope.cashbookList = [];
	$scope.cashbook = null;
	
//	$scope.patchPatient = function(patient, link){
//	patientServices.patchPatientParent(patient, link).then(function(response){
//		toastr.success('saved');
//	}).catch(function(response) {
//		console.error('Error',response);
//		toastr.error(response.data.message, 'Error');
//    });
//};
	
	$scope.deleteFromList = function(index){
		$scope.cashbookList.splice(index, 1);
	};
	
	$scope.addToList = function(){
		$scope.cashbookList.push($scope.cashbook);
		$scope.cashbook = null;
	};
	
	var paginationOptions = {
	        pageNumber: 0,
	        pageSize: 20,
	        sort: 'entryDate',
	        sortDirectColumn : 'entryDate.dir',
	        direction : 'desc',
	        link:_CONTEXT+'/api/cashBooks'
    };
	
	$scope.save = function(){
		formService.saveCashBook($scope.cashbookList).then(function(response){
			toastr.success('saved');
			$scope.cashbookList = [];
			$scope.getAllCashBook();
		}).catch(function(response) {
 			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.getAllCashBook = function(){
		var param = {'page':paginationOptions.pageNumber, 'size':paginationOptions.pageSize, 'sort':paginationOptions.sort, 'entryDate.dir':paginationOptions.direction};
	     
	    formService.genericGetSort(paginationOptions.link, param).then(function(response){
				$scope.cashbookGridOptions.data = response.data._embedded.cashBooks;
				$scope.cashbookGridOptions.totalItems = response.data.page.totalElements;
	 	}).catch(function(response) {
	 			console.error('Error',response);
	 			toastr.error(response.data.message, 'Error');
	 	});
	};
	
	$scope.cashbookGridOptions = {
            paginationPageSizes: [5, 10, 20],
            paginationPageSize: paginationOptions.pageSize,
            enableColumnMenus:false,
            useExternalPagination: true,
            columnDefs: [
                {name:'วันที่', field : 'entryDate', width: 100, enableSorting: true},
                {name:'หมายเลขรายการ', field : 'transactionCode', width: 100},
    			{name:'เอกสารเลขที่', field : 'docNo', width: 100},
    			{name:'เล่มที่', field : 'bookNo'},
    			{name:'รายการ', field : 'description'},
    			{name:'รายรับ', field : 'debit'},
    			{name:'เงินงบประมาณ', field : 'creditBudget'},
    			{name:'เงินรายได้แผ่นดิน', field : 'creditRevenue'},
    			{name:'เงินนอกงบประมาณ', field : 'creditNbudget'},
    			{name:'หมายเหตุ', field : 'remark'},
    			{name:'บันทึกโดย', field : 'recordBy'},
    			{name:'ตรวจทานโดย', field : 'reviewBy'},
    			{name:'ยืนยันโดย', field : 'approveBy'},
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
                   
                   var param = {'page':newPage-1, 'size':pageSize, 'sort':paginationOptions.sort, 'entryDate.dir':paginationOptions.direction};
                   formService.genericGetSort(paginationOptions.link, param).then(function(response){
           			$scope.cashbookGridOptions.data = response.data._embedded.cashBooks;
           			$scope.cashbookGridOptions.totalItems = response.data.page.totalElements;
    	       		}).catch(function(response) {
    	       			console.error('Error',response);
    	       			toastr.error(response.data.message, 'Error');
    	       	    });
                });
            }
        };
	
	
  }
]);