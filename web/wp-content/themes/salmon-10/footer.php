</div> 
<!-- container END -->

</div>

<!-- footer START -->
<div class="FooterContain">
    
    <div class="Footer">
        
        <div class="FooterRecent">
        <?
            $today = current_time('mysql', 1);
            if ( $recentposts = $wpdb->get_results("SELECT ID, post_title FROM $wpdb->posts WHERE post_status = 'publish' AND post_date_gmt < '$today' ORDER BY post_date DESC LIMIT 0, 5")):
            ?><h2>Recent Articles</h2>
            <ul>
                        <?php foreach ($recentposts as $post) { if ($post->post_title == '') $post->post_title = sprintf(__('Post #%s'), $post->ID);
                                            echo "<li><a href='".get_permalink($post->ID)."'>"; the_title(); echo"</a><small>"; the_date();   echo "</small></li>"; } ?>
            </ul>
            <?php endif; ?>
        </div>
        <!--
        <div class="FooterCommented">
            <h2>Recently Discussed Articles</h2>
            <ul>
                <li><a href="http://dev.wp-plugins.org/browser/recent-comments/">Recent Comments</a> Plugin Required</li>
            </ul>
        </div>
        -->
        
        
        <div class="FooterPartners">
            <h2>My Links</h2>
            <ul>
            <?php get_links('-1', '<li>', '</li>', '<br />', FALSE, 'id', FALSE, TRUE, -1, TRUE); ?>
            </ul>
        </div>
        
        <div class="FooterCopy">
            <p>Powered by <a href="http://wordpress.org" title="wordpress">Wordpress</a> and <a href="http://www.justinshattuck.com/salmon/" title="Salmon Wordpress Theme by Justin Shattuck">Salmon</a> by <a href="http://www.justinshattuck.com" title="justin shattuck">Justin Shattuck</a>.</p>
        </div>
    </div>
</div>
<?php wp_footer(); ?>
</body>
</html>
