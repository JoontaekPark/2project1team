export class KakaoMap {
    constructor(containerId, centerAddress = '제주특별자치도 제주시 첨단로 242', content, level = 3) {
        this.container = document.getElementById(containerId);
        this.level = level;
        this.geocoder = new kakao.maps.services.Geocoder();
        this.map = null;
        this.initializeMap(centerAddress, content);
    }

    initializeMap(address, content) {
        this.geocoder.addressSearch(address, (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
                const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                const options = {
                    center: coords,
                    level: this.level
                };
                this.map = new kakao.maps.Map(this.container, options);
                this.addMarker(coords, content);
            } else {
                console.error('초기 지도 설정에 실패했습니다:', status);
            }
        });
    }

    addMarker(coords, content) {
        const marker = new kakao.maps.Marker({
            map: this.map,
            position: coords
        });
        const infowindow = new kakao.maps.InfoWindow({
            content: `<div style="width:150px;text-align:center;padding:6px 0;">${content}</div>`
        });
        infowindow.open(this.map, marker);
    }

    searchAddress(address, markerContent = '검색된 위치') {
        this.geocoder.addressSearch(address, (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
                const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                this.addMarker(coords, markerContent);
                this.map.setCenter(coords);
            } else {
                console.error('주소 검색에 실패했습니다:', status);
            }
        });
    }
}