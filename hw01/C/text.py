def get_command(x):
    curl_command = "curl 'http://1d3p.wp.codeforces.com/new' \
  -H 'Connection: keep-alive' \
  -H 'Cache-Control: max-age=0' \
  -H 'Upgrade-Insecure-Requests: 1' \
  -H 'Origin: http://1d3p.wp.codeforces.com' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36' \
  -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9' \
  -H 'Referer: http://1d3p.wp.codeforces.com/' \
  -H 'Accept-Language: en-US,en;q=0.9,ru;q=0.8,bg;q=0.7' \
  -H 'Cookie: __utmc=71512449; evercookie_cache=yc3fodovdhrqw8yh3d; evercookie_png=yc3fodovdhrqw8yh3d; evercookie_etag=yc3fodovdhrqw8yh3d; 70a7c28f3de=yc3fodovdhrqw8yh3d; __utma=71512449.322493438.1599922814.1600272174.1600363223.5; __utmz=71512449.1600363223.5.2.utmcsr=youtube.com|utmccn=(referral)|utmcmd=referral|utmcct=/; JSESSIONID=299898422722B10F7D08CB9A47995FD8' \
  --data-raw '_af=34be50b38beccce4&proof=" + str(x * x) + "&amount="+ str(x) + "&submit=Submit' \
  --compressed \
  --insecure"
    return curl_command
