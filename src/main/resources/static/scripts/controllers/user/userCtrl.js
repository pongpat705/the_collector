
'use strict';
angular
	.module('app')
		.controller('userCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'userService',
									'$mdDialog',
  function userCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, userService,
		  			$mdDialog) {
	
	$scope.$watch("init", function(){
			$scope.getAllUser(0, 20);
		
	});
	
	var paginationOptions = {
	        pageNumber: 0,
	        pageSize: 20,
	        sort: null
    };
	
	var isRoleAdmin = $rootScope.checkPermission('ROLE_ADMIN');
	
	$scope.getAllUser = function(page, size){
		userService.getAllUser(page, size).then(function(response){
			$scope.userGridOptions.data = response.data._embedded.users;
			$scope.userGridOptions.totalItems = response.data.page.totalElements;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
	    });
	};
	$scope.addNewUser = function(ev){
		$mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'views/app/user/dialog.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      locals: {
		          mode : 'add'
		       },
		      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
		    })
		    .then(function() {
		    	$scope.getAllUser(paginationOptions.pageNumber, paginationOptions.pageSize);
		    }, function() {
		    	console.log('dialog closed');
		    });
	}
	$scope.patchUser = function(e, ev){
		$mdDialog.show({
		      controller: PatchDialogController,
		      templateUrl: 'views/app/user/dialog.html',
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      locals: {
		          userId: e.userId,
		          mode : 'edit'
		       },
		      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
		    })
		    .then(function() {
		    	$scope.getAllUser(paginationOptions.pageNumber, paginationOptions.pageSize);
		    }, function() {
		    	console.log('dialog closed');
		    });
	}
	$scope.del = function(e, ev){
		// Appending dialog to document.body to cover sidenav in docs app
	    var confirm = $mdDialog.confirm()
	          .title('Would you like to delete this user?')
	          .textContent('this action can not recovery.')
	          .ariaLabel('Lucky day')
	          .targetEvent(ev)
	          .ok('OK!')
	          .cancel('CANCEL');

	    $mdDialog.show(confirm).then(function() {
	      var userId = e.userId;
	      
	      	userService.delUsers(userId).then(function(response){
	      		toastr.success('User has been deleted', 'Info');
	      		$scope.getAllUser(paginationOptions.pageNumber, paginationOptions.pageSize);
			}).catch(function(response) {
				console.error('Error',response);
				toastr.error(response.data.message, 'Error');
		    });
	    }, function() {
	    	console.log('deleting canceled');
	    });
	};
	
	$scope.userGridOptions = {
            paginationPageSizes: [5, 10, 20],
            paginationPageSize: paginationOptions.pageSize,
            enableColumnMenus:false,
            enableAutoFitColumns: true,
            useExternalPagination: true,
            columnDefs: [
                { name: 'userName'},
    			{ name: 'name'},
    			 {
                    name : 'Edit',
                    cellTemplate : '<div class="ui-grid-cell-contents">' +
                                        '<button class="btn btn-xs btn-info" title="Edit this" ng-click="grid.appScope.patchUser(row.entity, $event);" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Edit</button>' +
                                   '</div>',
                    enableCellEdit : false
//                    ,
//                    visible : isRoleAdmin
                },
                {
                    name : 'Delete',
                    cellTemplate : '<div class="ui-grid-cell-contents">' +
                                        '<button class="btn btn-xs btn-danger" title="delete this" ng-click="grid.appScope.del(row.entity, $event);" ><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</button>' +
                                   '</div>',
                    enableCellEdit : false
//                    ,
//                    visible : isRoleAdmin
                }
    			
            ],
            onRegisterApi: function(gridApi) {
               $scope.gridApi = gridApi;
               gridApi.pagination.on.paginationChanged(
                 $scope, 
                 function (newPage, pageSize) {
                   paginationOptions.pageNumber = newPage;
                   paginationOptions.pageSize = pageSize;
                   
                   userService.getAllUser(newPage-1, pageSize).then(function(response){
           			$scope.userGridOptions.data = response.data._embedded.users;
           			$scope.userGridOptions.totalItems = response.data.page.totalElements;
    	       		}).catch(function(response) {
    	       			console.error('Error',response);
    	       			toastr.error(response.data.message, 'Error');
    	       	    });
                });
            }
        };
	
	function DialogController($scope, $rootScope, $mdDialog, userService, toastr, mode) {
		$scope.mode = mode;
		$scope.combo = _ROLE;
		
		$scope.user = {};
		$scope.user.role = [];
		$scope.addRole = function(xRole){
			$scope.user.role.push(xRole);
		};
		$scope.deRole = function(index){
			$scope.user.role.splice(index, 1);
		};
		
	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };

	    $scope.add = function(user) {
	    	userService.addUser(user).then(function(response){
	    		toastr.success('Added', 'Info');
	    		$scope.hide();
			}).catch(function(response) {
				console.error('Error',response);
				toastr.error(response.data.message, 'Error');
				$scope.hide();
		    });
	      
	    };
	  }
	
	function PatchDialogController($scope, $rootScope, $mdDialog, userService, toastr, userId, mode) {
		$scope.mode = mode;
		$scope.combo = _ROLE;
		
		
		userService.getUserForpatch(userId).then(function(response){
      		$scope.user = response.data;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
	    });
		
		
		$scope.addRole = function(xRole){
			$scope.user.role.push(xRole);
		};
		$scope.deRole = function(index){
			$scope.user.role.splice(index, 1);
		};
		
	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };

	    $scope.add = function(user) {
	    	userService.patchUser(user, userId).then(function(response){
	    		toastr.success('Updated', 'Info');
	    		$scope.hide();
			}).catch(function(response) {
				console.error('Error',response);
				toastr.error(response.data.message, 'Error');
				$scope.hide();
		    });
	      
	    };
	  }
  }
]);