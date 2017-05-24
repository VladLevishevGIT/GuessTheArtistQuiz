var quizMainApp = angular.module("QuizApp", [])

quizMainApp.controller("QuizAppCtrl", function ($http, $scope) {
    $scope.firstSongCount = true;
    $scope.secondSongCount = true;
    $scope.roundScore = 50;
    $scope.totalScore = 0;
    $scope.roundNumber = 1;
    $http.get('rest/quizWebService/getFirstSong01').
    then(function(response) {
        $scope.returnedRound = response.data;
        $scope.firstSong = $scope.returnedRound.firstSong;
        $scope.artistName = $scope.returnedRound.artistName;
    });

    $scope.getOneMoreSong = function (inputValue) {
            if (inputValue == $scope.returnedRound.artistName) {
                if($scope.roundNumber < 5) {
                    $scope.showSuccessAlert = true;
                    $scope.successTextAlert = "Very Good!"
                    $scope.showNextRoundButton = true;
                    $scope.hideNextSongButton = true;
                }else {
                    $scope.qiuzMainPanel = true;
                    $scope.totalScore = $scope.roundScore + $scope.totalScore;
                }
            } else if ($scope.firstSongCount) {
                $scope.firstSong = $scope.returnedRound.firstSong;
                $scope.secondSong = $scope.returnedRound.secondSong;
                $scope.firstSongCount = false;
                $scope.roundScore = 30;
            } else if ($scope.secondSongCount) {
                $scope.firstSong = $scope.returnedRound.firstSong;
                $scope.secondSong = $scope.returnedRound.secondSong;
                $scope.thirdSong = $scope.returnedRound.thirdSong;
                $scope.imageURL = $scope.returnedRound.imageURL;
                $scope.secondSongCount = false;
                $scope.roundScore = 20;
            } else {
                if($scope.roundNumber < 5) {
                    $scope.showErrorAlert = true;
                    $scope.showNextRoundButton = true;
                    $scope.hideNextSongButton = true;
                    $scope.roundScore = 0;
                }else {
                    $scope.qiuzMainPanel = true;
                    $scope.roundScore = 0;
                    $scope.totalScore = $scope.roundScore + $scope.totalScore;
                }
        }
    }

    $scope.nextRound = function () {
            $scope.showSuccessAlert = false;
            $scope.showErrorAlert = false;
            $scope.showNextRoundButton = false;
            $scope.hideNextSongButton = false;
            $scope.secondSong = null;
            $scope.thirdSong = null;
            $scope.inputValue = "";
            $scope.imageURL = null;
            $scope.firstSongCount = true;
            $scope.secondSongCount = true;
            $scope.totalScore = $scope.roundScore + $scope.totalScore;
            $scope.roundScore = 50;
            $scope.roundNumber = $scope.roundNumber + 1;
            $http.get('rest/quizWebService/getFirstSong01').then(function (response) {
                $scope.returnedRound = response.data;
                $scope.firstSong = $scope.returnedRound.firstSong;
                $scope.artistName = $scope.returnedRound.artistName;
            });
    }

    $scope.backLinkClick = function () {
        window.location.reload(false);
    };
})