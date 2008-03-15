<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head profile="http://gmpg.org/xfn/11">
    
    <title><?php 
        wp_title('');
        if(wp_title('',false))
            echo ' - ';
        bloginfo('name');
        if ( is_single() ) { ?> 
        &raquo; Blog Archive 
        <?php } 
        ?></title>
    
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" /> 
    <link rel="pingback" href="<?php bloginfo('pingback_url'); ?>" />
    <link rel="shorcut icon" type="image/x-ico" href="<?php bloginfo('template_url'); ?>/favicon.ico" />
    <link href="<?php bloginfo('stylesheet_url'); ?>" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<?php bloginfo('template_url'); ?>/javascript/imghover.js"> </script>
    <script type="text/javascript" src="<?php bloginfo('template_url'); ?>/javascript/input.js"> </script>
    <script type="text/javascript" src="<?php bloginfo('template_url'); ?>/javascript/rounded.js"> </script>
    
    <meta name="generator" content="WordPress <?php bloginfo('version'); ?>" />
    <meta name="robots" content="all" />
    <meta name="author" content="Justin Shattuck" />
    <meta name="copyright" content="Copyright (c) Justin Shattuck" />
    
    <?php wp_head(); ?>
    
</head>
<body>

<!-- header START -->

<div class="Header">
    <div class="TopHeader">
        <a href="<?php echo get_settings('home'); ?>"><?php bloginfo('name'); ?></a>
    </div>
    
    <h1><a href="<?php echo get_settings('home'); ?>" title="<?php bloginfo('name'); ?>"><?php bloginfo('name'); ?></a></h1>
    <h3><?php bloginfo('description'); ?></h3>
    
    <div class="MainMenu">
        <ul>
            <li>
                <a style="background-position: left bottom;" href="#">
                    <span style="background-position: right bottom;"><?php
                        if(wp_title('', false))
                            wp_title('');
                        else
                            echo 'Home';
                    ?></span>
                </a>
            </li>
            <!--<li><a href="#"><span>Archives</span></a></li>
            <li><a href="#"><span>Contact</span></a></li>-->
        </ul>
    </div>
    
    <div class="Search">
        
        <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
            <fieldset>
                <legend>Search</legend>
                <input name="keyword" class="keyword" />
                <div id="buttonsearch"><input name="submit" type="image" class="search" onmouseover="javascript:changeSty('searchIE');" onmouseout="javascript:changeSty('search');" title="Search" src="<?php bloginfo('template_url'); ?>/images/transparent.gif" alt="Search" />
                </div>
            </fieldset>
        </form>
        
    </div>
    
    <div class="SubRss">
        <ul>
            <li><a class="subscribe" href="<?php bloginfo('rss2_url'); ?>"><em>Subscribe to RSS</em></a></li>
        </ul>
    </div>
    
    
</div> 
<!-- header END -->