
'use strict';
angular
	.module('app')
		.controller('balanceListCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function balanceListCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
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
                {name:'วันที่', field : 'createDate', width: 100, enableSorting: true},
                {name:'ส่วนงานราชการ', field : 'department', width: 100},
    			{name:'อำเภอ', field : 'amphur', width: 100},
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
	
  }
]);