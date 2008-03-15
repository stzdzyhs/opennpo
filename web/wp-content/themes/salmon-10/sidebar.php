<!-- Side Right - START -->
<div class="SR">
    <div class="Categ">
        <h2>Pages</h2>
        <ul>
        <?php wp_list_pages('exclude=&sort_column=ID&title_li='); ?> 
        </ul>
        <h2>Categories</h2>
        <ul>
        <?php wp_list_cats('sort_column=name&optioncount=0&hierarchical=0&hide_empty=0'); ?>
        </ul>
    </div> 
    
    
    <!--
    <div class="Flickr">
        <h2>Flickr Photostream</h2>
        <ul>
            <li><script type="text/javascript" src="http://www.flickr.com/badge_code.gne?nsid=30325267@N00&amp;count=6&amp;display=latest&amp;name=0&amp;size=square&amp;raw=1"></script></li> 
        </ul>
    </div>
    -->
    
    <div class="Banners">
    </div>
    
    <div class="Syndication">
        <h2>Syndicate</h2>
        <ul>
            <li><a class="RSS" href="<?php bloginfo('rss2_url'); ?>"><em>RSS</em></a></li>
            <li><a class="BlogMarks" href="<?php echo get_settings('home'); ?>"><em>BlogMarks</em></a></li>
            <li><a class="delicius" href="<?php echo get_settings('home'); ?>"><em>del.ici.us</em></a></li>
            <li><a class="NewsWine" href="<?php echo get_settings('home'); ?>"><em>NewsWine</em></a></li>
            <li><a class="Simpy" href="<?php echo get_settings('home'); ?>"><em>Simpy</em></a></li>
            <li><a class="Spurl" href="<?php echo get_settings('home'); ?>"><em>Spurl</em></a></li>
            <li><a class="comments" href="<?php echo get_settings('home'); ?>"><em>co.mments</em></a></li>
        </ul>
    </div>
    
</div> 
<!-- Side Right - END -->
