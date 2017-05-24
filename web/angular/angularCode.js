var mainApp = angular.module('myApp', [])

mainApp.controller('myCtrl', 
		
function($scope, $http) {
	
    $http.get('rest/quizWebService/getFirstSong').
        then(function(response) {
            $scope.round = response.data
        });
        
    $scope.getOneMoreSong = function(inputValue){
    	$http.get('rest/quizWebService/getOneMoreSong/'+inputValue)
    	.then(function(response){
    		returnedRound = response.data
    		if(returnedRound.firstSong != null){
    			$scope.round = response.data
    			$scope.inputValue = null
    		} else {
    			if (returnedRound.roundNumber < 5){
    			$scope.round = response.data
    			$scope.inputValue = null
    			$scope.hideNextSongButton = true
    	    	$scope.showNextRoundButton = true
    			$scope.successTextAlert = "Correct!!!!!";
    			$scope.showSuccessAlert = true;
    			} else {
    				$scope.showFinalPanel = true;
    				$scope.hideNextSongButton = true
    			}
    		}
    	});
    };
    
    $scope.nextRound = function(inputValue){
    	$scope.hideNextSongButton = false
    	$scope.showNextRoundButton = false
    	$scope.showSuccessAlert = false;
    	$http.get('rest/quizWebService/getFirstSong').
        then(function(response) {
            $scope.round = response.data
        });
    };
});

