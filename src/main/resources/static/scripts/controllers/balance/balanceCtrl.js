
'use strict';
angular
	.module('app')
		.controller('balanceCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function balanceCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.functions = $rootScope.functions;
	});
	
	$scope.dropdownBalance = $rootScope.dropdownBalance;
	$scope.mapBalance = $rootScope.mapBalance;
	
	$scope.balanceList = [];
	$scope.balanceRow = null;
	$scope.balance = {};
	$scope.deleteFromList = function(index){
		$scope.balanceList.splice(index, 1);
	};
	
	$scope.addToList = function(){
		$scope.balanceList.push($scope.balanceRow);
		$scope.balanceRow = null;
	};
	
	$scope.save = function(){
		$scope.balance.balances = $scope.balanceList;
		
		formService.saveBalanceMaster($scope.balance).then(function(response){
			toastr.success('saved');
			$scope.balanceList = [];
			$scope.balance = {};
		}).catch(function(response) {
 			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
 	});
	};
	
  }
]);