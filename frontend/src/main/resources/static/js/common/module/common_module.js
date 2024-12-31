import Api from '/static/js/common/module/api_module.js';
import {input} from '/static/js/common/module/utills/input.js';
import {number} from '/static/js/common/module/utills/number.js';
import {date} from '/static/js/common/module/utills/date.js';
import SessionStorageManager from '/static/js/common/module/session_module.js';

export const api = Api; // API 모듈 내보내기
export const sessionStorage = SessionStorageManager;

export const utills = {
    input,
    number,
    date
};
