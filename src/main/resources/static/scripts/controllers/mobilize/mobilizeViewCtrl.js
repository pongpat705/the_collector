
'use strict';
angular
	.module('app')
		.controller('mobilizeViewCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', '$window',
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function mobilizeViewCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, $window,
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.loadMobilizeReport();
	});
	$scope.mode = 'view';
	
	$scope.mobilizeList = [];
	$scope.mobilizeRow = null;
	$scope.mobilize = {};
	
	$scope.loadMobilizeReport = function(){
		console.log($stateParams.mobilizeId);
		formService.loadMobilizeMaster($stateParams.mobilizeId).then(function(response){
			$scope.mobilize = response.data;
			$scope.mobilizeList = response.data.mobilizes;
		}).catch(function(response){
			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
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
		}).catch(function(response) {
 			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
	$scope.printPdf = function(e){
		 $window.location.href = _CONTEXT+'/mobilize/'+e.mobilizeId+'/pdf';
	};
	
  }
]);