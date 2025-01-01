/**
 * SessionStorageManager
 *
 * 세션 스토리지를 쉽게 관리할 수 있는 유틸리티 모듈.
 *
 * 사용법:
 * 1. 데이터 저장:
 *    SessionStorageManager.set('key', { name: 'value' });
 *
 * 2. 데이터 가져오기:
 *    const data = SessionStorageManager.get('key');
 *
 * 3. 데이터 삭제:
 *    SessionStorageManager.remove('key');
 *
 * 4. 모든 데이터 초기화:
 *    SessionStorageManager.clear();
 */

class SessionStorage {
    /**
     * 세션 스토리지에 데이터를 저장합니다.
     * @param {string} key - 데이터 키.
     * @param {*} value - 저장할 데이터 (객체, 배열, 문자열 등).
     */
    static set(key, value) {
        try {
            const serializedValue = JSON.stringify(value);
            sessionStorage.setItem(key, serializedValue);
        } catch (error) {
            console.error(`세션 스토리지 저장 오류: ${key}`, error);
        }
    }

    /**
     * 세션 스토리지를 가져옵니다.
     * @param {string} key - 데이터 키.
     * @returns {*} 저장된 데이터 (없을 경우 null).
     */
    static get(key) {
        try {
            const serializedValue = sessionStorage.getItem(key);
            if (serializedValue === null) return null;
            return JSON.parse(serializedValue);
        } catch (error) {
            console.error(`세션 스토리지를 가져오는 중 오류 발생: ${key}`, error);
            return null;
        }
    }

    /**
     * 세션 스토리지에서 특정 데이터를 삭제합니다.
     * @param {string} key - 삭제할 데이터의 키.
     */
    static remove(key) {
        try {
            sessionStorage.removeItem(key);
        } catch (error) {
            console.error(`세션 스토리지를 삭제하는 중 오류 발생: ${key}`, error);
        }
    }

    /**
     * 세션 스토리지를 초기화합니다 (모든 데이터를 삭제).
     */
    static clear() {
        try {
            sessionStorage.clear();
        } catch (error) {
            console.error('세션 스토리지를 초기화하는 중 오류 발생:', error);
        }
    }
}

export default SessionStorage;
