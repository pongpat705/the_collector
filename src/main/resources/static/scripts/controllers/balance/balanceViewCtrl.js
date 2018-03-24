
'use strict';
angular
	.module('app')
		.controller('balanceViewCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', '$window',
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function balanceViewCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, $window,
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.loadBlanceReport();
	});
	$scope.mode = 'view';
	
	$scope.dropdownBalance = $rootScope.dropdownBalance;
	$scope.mapBalance = $rootScope.mapBalance;
//	
	$scope.balanceList = [];
	$scope.balanceRow = null;
	$scope.balance = {};
	
	$scope.loadBlanceReport = function(){
		console.log($stateParams.balanceId);
		formService.loadBalanceMaster($stateParams.balanceId).then(function(response){
			$scope.balance = response.data;
			$scope.balanceList = response.data.balances;
		}).catch(function(response){
			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
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
		}).catch(function(response) {
 			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.printPdf = function(e){
		 $window.location.href = _CONTEXT+'/balance/'+e.masterId+'/pdf';
	};
	
  }
]);