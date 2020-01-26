package immatrikulation.servicetaskdelegation.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class SendToTwitter {

    public void doSendTwitter(String content) throws TwitterException {
        AccessToken accessToken = new AccessToken("220324559-jet1dkzhSOeDWdaclI48z5txJRFLCnLOK45qStvo", "B28Ze8VDucBdiE38aVQqTxOyPc7eHunxBVv7XgGim4say");
        Twitter twittermsg = new TwitterFactory().getInstance();
        twittermsg.setOAuthConsumer("lRhS80iIXXQtm6LM03awjvrvk", "gabtxwW8lnSL9yQUNdzAfgBOgIMSRqh7MegQs79GlKVWF36qLS");
        twittermsg.setOAuthAccessToken(accessToken);
        twittermsg.updateStatus(content);
    }
}
