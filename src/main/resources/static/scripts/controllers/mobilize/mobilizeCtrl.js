
'use strict';
angular
	.module('app')
		.controller('mobilizeCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function mobilizeCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.functions = $rootScope.functions;
	});
	
	$scope.mode = 'add';
	
	
	$scope.mobilizeList = [];
	$scope.mobilizeRow = null;
	$scope.mobilize = {};
	$scope.deleteFromList = function(index){
		$scope.mobilizeList.splice(index, 1);
	};
	
	$scope.addToList = function(){
		
		$scope.mobilizeRow.no = $scope.mobilizeList.length+1;
		$scope.mobilizeList.push($scope.mobilizeRow);
		$scope.mobilizeRow = null;
	};
	
	$scope.save = function(){
		$scope.mobilize.mobilizes = $scope.mobilizeList;
		
		formService.saveMobilizeMaster($scope.mobilize).then(function(response){
			toastr.success('saved');
			$scope.mobilizeList = [];
			$scope.mobilize = {};
		}).catch(function(response) {
 			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
  }
]);