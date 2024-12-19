(function($) {
    'use strict';
    var $button = $('.show-popup-employee'),
      $tabledialog = $('#employee-popup');
  
    $button.on('click', function() {
      $tabledialog[0].showModal();
    });
  
    $('.cancel').on('click', function() {
      $tabledialog[0].close();
    });
    console.log('clicked');
  })(jQuery);

  (function($) {
    'use strict';
    var $button = $('.show-popup-course'),
      $tabledialog = $('#course-popup');
  
    $button.on('click', function() {
      $tabledialog[0].showModal();
    });
  
    $('.cancel').on('click', function() {
      $tabledialog[0].close();
    });
    console.log('clicked');
  })(jQuery);