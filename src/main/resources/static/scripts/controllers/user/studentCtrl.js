
'use strict';
angular
	.module('app')
		.controller('studentCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'userService',
									'$mdDialog',
  function studentCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, userService,
		  			$mdDialog) {
	
	$scope.$watch("init", function(){
			$scope.getAllActiveStudent(0, 20);
		
	});
	
	var paginationOptions = {
	        pageNumber: 0,
	        pageSize: 20,
	        sort: null
    };
	
	var isRoleAdmin = $rootScope.checkPermission('ROLE_ADMIN');
	
	$scope.getAllActiveStudent = function(page, size){
		userService.getAllActiveStudent(page, size).then(function(response){
			$scope.userGridOptions.data = response.data._embedded.students;
			$scope.userGridOptions.totalItems = response.data.page.totalElements;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
	    });
	};
	
	$scope.uploadStudent = function(){
		var x = document.getElementById("myFile");
		var file = x.files[0];
		console.log(file);
		x.disabled = true;
		userService.uploadFile(file).then(function(response){
			$scope.getAllActiveStudent(0, 20);
			x.disabled = false;
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.del = function(e, ev){
		// Appending dialog to document.body to cover sidenav in docs app
	    var confirm = $mdDialog.confirm()
	          .title('Would you like to delete this student?')
	          .textContent('this action can not recovery.')
	          .ariaLabel('Lucky day')
	          .targetEvent(ev)
	          .ok('OK!')
	          .cancel('CANCEL');

	    $mdDialog.show(confirm).then(function() {
	      var userId = e.userId;
	      
	      	userService.delUsers(userId).then(function(response){
	      		toastr.success('Student has been deleted', 'Info');
	      		$scope.getAllActiveStudent(paginationOptions.pageNumber, paginationOptions.pageSize);
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
                { name: 'stName'},
    			{ name: 'stNatid'},
    			{ name: 'stGrade'},
    			{ name: 'active'},
    			{ name: 'createdBy'},
    			{ name: 'createdDate'},
    			{ name: 'updatedBy'},
    			{ name: 'updatedDate'},
    			
            ],
            onRegisterApi: function(gridApi) {
               $scope.gridApi = gridApi;
               gridApi.pagination.on.paginationChanged(
                 $scope, 
                 function (newPage, pageSize) {
                   paginationOptions.pageNumber = newPage;
                   paginationOptions.pageSize = pageSize;
                   
                   userService.getAllActiveStudent(newPage-1, pageSize).then(function(response){
           			$scope.userGridOptions.data = response.data._embedded.students;
           			$scope.userGridOptions.totalItems = response.data.page.totalElements;
    	       		}).catch(function(response) {
    	       			console.error('Error',response);
    	       			toastr.error(response.data.message, 'Error');
    	       	    });
                });
            }
        };
	
	
  }
]);