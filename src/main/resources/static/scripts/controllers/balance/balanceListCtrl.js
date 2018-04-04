
'use strict';
angular
	.module('app')
		.controller('balanceListCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', '$window',
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function balanceListCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, $window,
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.loadBalanceList();
	});
	
	var paginationOptions = {
	        pageNumber: 0,
	        pageSize: 20,
	        sort: 'createDate',
	        sortDirectColumn : 'createDate.dir',
	        direction : 'desc',
	        link:_CONTEXT+'/api/balanceMasters'
    };
	
	
	$scope.loadBalanceList = function(){
		var param = {'page':paginationOptions.pageNumber, 'size':paginationOptions.pageSize, 'sort':paginationOptions.sort, 'createDate.dir':paginationOptions.direction};
		formService.genericGetSort(paginationOptions.link, param).then(function(response){
			$scope.balanceGridOptions.data = response.data._embedded.balanceMasters;
			$scope.balanceGridOptions.totalItems = response.data.page.totalElements;
		}).catch(function(response){
			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
	
	$scope.balanceGridOptions = {
            paginationPageSizes: [5, 10, 20],
            paginationPageSize: paginationOptions.pageSize,
            enableColumnMenus:false,
            enableAutoFitColumns: true,
            useExternalPagination: true,
            columnDefs: [
                {name:'วันที่', field : 'createDate', enableSorting: true},
                {name:'ส่วนงานราชการ', field : 'department'},
    			{name:'อำเภอ', field : 'amphur'},
    			{
                    name : 'ดูรายงาน',
                    cellTemplate : '<div class="ui-grid-cell-contents">' +
                                        '<button class="btn btn-xs btn-primary" title="Edit this" ng-click="grid.appScope.viewReport(row.entity, $event);" ><i class="fa fa-file" aria-hidden="true"></i> ดู</button>' +
                                   '</div>',
                                   width: 100,
                    enableCellEdit : false
//                    ,visible : isRoleAdmin
                },
    			{
                    name : 'พิมพ์รายงาน',
                    cellTemplate : '<div class="ui-grid-cell-contents">' +
                                        '<button class="btn btn-xs btn-danger" title="Edit this" ng-click="grid.appScope.printPdf(row.entity, $event);" ><i class="fa fa-print" aria-hidden="true"></i> พิมพ์</button>' +
                                   '</div>',
                                   width: 100,
                    enableCellEdit : false
//                    ,visible : isRoleAdmin
                }
            ],
            onRegisterApi: function(gridApi) {
               $scope.gridApi = gridApi;
               gridApi.pagination.on.paginationChanged(
                 $scope, 
                 function (newPage, pageSize) {
                   paginationOptions.pageNumber = newPage;
                   paginationOptions.pageSize = pageSize;
                   
                   var param = {'page':paginationOptions.pageNumber, 'size':paginationOptions.pageSize, 'sort':paginationOptions.sort, 'createDate.dir':paginationOptions.direction};
                   formService.genericGetSort(paginationOptions.link, param).then(function(response){
           			$scope.balanceGridOptions.data = response.data._embedded.balanceMasters;
           			$scope.balanceGridOptions.totalItems = response.data.page.totalElements;
    	       		}).catch(function(response) {
    	       			console.error('Error',response);
    	       			toastr.error(response.data.message, 'Error');
    	       	    });
                });
            }
        };
	
	$scope.printPdf = function(e, ev){
		 $window.location.href = _CONTEXT+'/balance/'+e.masterId+'/pdf';
	};
	
	$scope.viewReport = function(e, ev){
		$state.go('app.balance.view',{balanceId:e.masterId},{reload:true});
	};
	
  }
]);