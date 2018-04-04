
'use strict';
angular
	.module('app')
		.controller('mobilizeListCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate',  '$window',
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function mobilizeListCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, $window,
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.loadMobilizeList();
	});
	
	var paginationOptions = {
	        pageNumber: 0,
	        pageSize: 20,
	        sort: 'createdDate',
	        sortDirectColumn : 'createdDate.dir',
	        direction : 'desc',
	        link:_CONTEXT+'/api/mobilizeMasters'
    };
	
	
	$scope.loadMobilizeList = function(){
		var param = {'page':paginationOptions.pageNumber, 'size':paginationOptions.pageSize, 'sort':paginationOptions.sort, 'createDate.dir':paginationOptions.direction};
		formService.genericGetSort(paginationOptions.link, param).then(function(response){
			$scope.mobilizeGridOptions.data = response.data._embedded.mobilizeMasters;
			$scope.mobilizeGridOptions.totalItems = response.data.page.totalElements;
		}).catch(function(response){
			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
	
	$scope.mobilizeGridOptions = {
            paginationPageSizes: [5, 10, 20],
            paginationPageSize: paginationOptions.pageSize,
            enableColumnMenus:false,
            enableAutoFitColumns: true,
            useExternalPagination: true,
            columnDefs: [
                {name:'วันที่', field : 'createdDate', enableSorting: true},
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
           			$scope.mobilizeGridOptions.data = response.data._embedded.mobilizeMasters;
           			$scope.mobilizeGridOptions.totalItems = response.data.page.totalElements;
    	       		}).catch(function(response) {
    	       			console.error('Error',response);
    	       			toastr.error(response.data.message, 'Error');
    	       	    });
                });
            }
        };
	
	$scope.printPdf = function(e, ev){
		 $window.location.href = _CONTEXT+'/mobilize/'+e.mobilizeId+'/pdf';
	};
	
	$scope.viewReport = function(e, ev){
		$state.go('app.mobilize.view',{mobilizeId:e.mobilizeId},{reload:true});
	};
	
  }
]);