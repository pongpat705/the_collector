
'use strict';
angular
	.module('app')
		.controller('incomeCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function incomeCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.loadIncomeList();
	});
	
	$scope.budgetList = [];
	$scope.budget = null;
	
	var paginationOptions = {
	        pageNumber: 0,
	        pageSize: 20,
	        sort: 'entryDate',
	        sortDirectColumn : 'entryDate.dir',
	        direction : 'asc',
	        link:_CONTEXT+'/api/schoolBudgets'
    };
	
	
	$scope.loadIncomeList = function(){
		var param = {'page':paginationOptions.pageNumber, 'size':paginationOptions.pageSize, 'sort':paginationOptions.sort, 'createDate.dir':paginationOptions.direction};
		formService.genericGetSort(paginationOptions.link, param).then(function(response){
			$scope.incomeGridOptions.data = response.data._embedded.schoolBudgets;
			$scope.incomeGridOptions.totalItems = response.data.page.totalElements;
		}).catch(function(response){
			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.deleteFromList = function(index){
		$scope.budgetList.splice(index, 1);
	};
	
	$scope.addToList = function(){
		$scope.budgetList.push($scope.budget);
		$scope.budget = null;
	};
	
	$scope.save = function(){
		formService.saveSchoolBudget($scope.budgetList).then(function(response){
			toastr.success('saved');
			$scope.budgetList = [];
			$scope.loadIncomeList();
		}).catch(function(response) {
 			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.recordChange = function(e){
		var income = e.income ? e.income:0;
		var extra = e.extra ? e.extra:0;
		var interest = e.interest ? e.interest :0;
		var other = e.other ? e.other :0;
		
		var sum = income+extra+interest+other;
		
		e.sum = sum;
	}
	
	$scope.incomeGridOptions = {
            paginationPageSizes: [5, 10, 20],
            paginationPageSize: paginationOptions.pageSize,
            enableColumnMenus:false,
            enableAutoFitColumns: true,
            useExternalPagination: true,
            columnDefs: [
                {name:'วันที่', field : 'entryDate', enableSorting: true},
                {name:'เอกสารเลขที่', field : 'docNo'},
    			{name:'เล่มที่', field : 'recordNo'},
    			{name:'รายการ', field : 'description'},
    			{name:'เงินรายได้สถานศึกษา', field : 'income'},
    			{name:'ผลประโยชน์อื่น ๆ', field : 'extra'},
    			{name:'ดอกเบี้ย', field : 'interest'},
    			{name:'เงินรายได้อื่น ๆ', field : 'other'},
    			{name:'รวม', field : 'sum'},
    			{name:'หมายเหจุ', field : 'remark'}
//    			{
//                    name : 'ดูรายงาน',
//                    cellTemplate : '<div class="ui-grid-cell-contents">' +
//                                        '<button class="btn btn-xs btn-primary" title="Edit this" ng-click="grid.appScope.viewReport(row.entity, $event);" ><i class="fa fa-file" aria-hidden="true"></i> ดู</button>' +
//                                   '</div>',
//                                   width: 100,
//                    enableCellEdit : false
////                    ,visible : isRoleAdmin
//                },
//    			{
//                    name : 'พิมพ์รายงาน',
//                    cellTemplate : '<div class="ui-grid-cell-contents">' +
//                                        '<button class="btn btn-xs btn-danger" title="Edit this" ng-click="grid.appScope.printPdf(row.entity, $event);" ><i class="fa fa-print" aria-hidden="true"></i> พิมพ์</button>' +
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
                   
                   var param = {'page':paginationOptions.pageNumber, 'size':paginationOptions.pageSize, 'sort':paginationOptions.sort, 'entryDate.dir':paginationOptions.direction};
                   formService.genericGetSort(paginationOptions.link, param).then(function(response){
           			$scope.incomeGridOptions.data = response.data._embedded.schoolBudgets;
           			$scope.incomeGridOptions.totalItems = response.data.page.totalElements;
    	       		}).catch(function(response) {
    	       			console.error('Error',response);
    	       			toastr.error(response.data.message, 'Error');
    	       	    });
                });
            }
        };
	
	$scope.printPdf = function(e, ev){
		console.log(e);
	};
	
  }
]);