/*
 * SimpleModal Basic Modal Dialog
 * http://simplemodal.com
 *
 * Copyright (c) 2013 Eric Martin - http://ericmmartin.com
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 */

jQuery(function ($) {
	// Load dialog on page load
	//$('#basic-modal-content').modal();

	// Load dialog on click
	$('.basic1').click(function (e) {
		$('#basic-modal-content1').modal();

		return false;
	});
	$('.basic2').click(function (e) {
		$('#basic-modal-content2').modal();

		return false;
	});
	$('.basic3').click(function (e) {
		$('#basic-modal-content3').modal();

		return false;
	});
	$('.basic4').click(function (e) {
		$('#basic-modal-content4').modal();

		return false;
	});
	$('.basic5').click(function (e) {
		$('#basic-modal-content5').modal();

		return false;
	});
	$('.basicbr').click(function (e) {
		$('#basic-modal-contentbrowser').modal();

		return false;
	});
	$('.basicmsg1').click(function (e) {
		$('#basic-modal-contentmes1').modal();

		return false;
	});
	$('.basicmsg2').click(function (e) {
		$('#basic-modal-contentmes2').modal();

		return false;
	});
	$('.basicmsg3').click(function (e) {
		$('#basic-modal-contentmes3').modal();

		return false;
	});
});