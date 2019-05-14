$(document).ready(function() {
    $('#homestead_reg_form').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'The name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 1,
                        max: 45,
                        message: 'Please enter at least  characters and no more than 45'
                    }
                }
            },
            price: {
                validators: {
                    notEmpty: {
                        message: 'The price is required and cannot be empty'
                    },
                    regexp: {
                        regexp: /^[0-9]{0,10}((\.[0-9]{0,3}))?$/,
                        message: 'Please, follow pattern: ##########.### #-unnecessary symbol.'
                    }
                }
            },
            peopleNumber: {
                validators: {
                    notEmpty: {
                        message: 'The people number is required and cannot be empty'
                    },
                    stringLength: {
                        min: 1,
                        max: 3,
                        message: 'Please enter at least 1 characters and no more than 3'
                    },
                    numeric: {
                        message: 'The people number must have only numbers'
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: 'The people number is required and cannot be empty'
                    }
                }
            }
        }
    });
});