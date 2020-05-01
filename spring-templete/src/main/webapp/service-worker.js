/*var PRE_CACHE_NAME = "캐시-스토리지1";
// 캐시하고 싶은 리소스
var urlsToCache = [
  "/css/base.css",
  "https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,700&display=swap&subset=korean"
];

// 서비스워커가 설치될 때
self.addEventListener("install", event => {
  // 캐시 등록 이벤트가 끝날 때까지 기다려
  event.waitUntil(
    // '캐시-스토리지1'을 연다.
    // @return {Promise} 연결된 Cache Database를 반환한다.
    caches
      .open(PRE_CACHE_NAME)
      .then(cache => {
        console.log("캐시 디비와 연결됨");
        // addAll 메소드로 내가 캐싱할 리소스를 다 넣어주자.
        return cache.addAll(urlsToCache);
      })
      .then(() => {
        // 설치 후에 바로 활성화 단계로 들어갈 수 있게 해준다.
        return self.skipWaiting();
      })
  );
});*/