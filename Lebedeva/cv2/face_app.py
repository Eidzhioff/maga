import cv2
from deepface import DeepFace
import time

class FaceAnalyzer:
    def __init__(self, camera_index=0, frame_skip=0):
        self.camera_index = camera_index
        self.frame_skip = frame_skip
        self.frame_count = 0
        self.cap = None
        self.last_analysis = None
        self.last_update_time = 0 
        self.cache_duration = 0.5 
        
    def initialize_camera(self):
        """
        Инициализация видеозахвата
        """

        self.cap = cv2.VideoCapture(self.camera_index)
        if not self.cap.isOpened():
            raise RuntimeError("Не удалось подключиться к камере")
        
    def analyze_frame(self, frame):
        """
        Анализ кадра на наличие лиц
        """
        try:
            analysis = DeepFace.analyze(
                img_path=frame,
                actions=['age', 'gender', 'emotion'],
                enforce_detection=False,
                detector_backend='opencv',
                silent=True
            )
            
            if isinstance(analysis, list) and len(analysis) > 0:
                return analysis[0]
        except Exception as e:
            pass
            
        return None
    
    def draw_results(self, frame, results):
        """
        Отрисовка результатов на кадре
        """
        if not results or 'region' not in results:
            return frame
            
        x = results['region']['x']
        y = results['region']['y']
        w = results['region']['w']
        h = results['region']['h']
        
        cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 255), 2)
        
        age = results.get('age', 'N/A')
        gender = results.get('dominant_gender', 'N/A')
        emotion = results.get('dominant_emotion', 'N/A')
        
        info_lines = [
            f"Age: {age}",
            f"Gender: {gender}",
            f"Emotion: {emotion}"
        ]
        
        for i, line in enumerate(info_lines):
            y_pos = y + h + 20 + i * 25
            cv2.putText(frame, line, (x, y_pos),
                       cv2.FONT_HERSHEY_DUPLEX, 0.7, (0, 0, 255), 2)
            
        return frame
    
    def should_update_analysis(self):
        """
        Проверяет, нужно ли обновлять анализ
        """
        current_time = time.time()
        
        if self.last_analysis is None:
            return True
            
        if self.frame_count % self.frame_skip == 0:
            return True
            
        if current_time - self.last_update_time > self.cache_duration:
            return True
            
        return False
    
    def run(self):
        """
        Основной цикл анализа
        """     
        while True:
            ret, frame = self.cap.read()
            if not ret:
                print("Ошибка: не удалось получить кадр")
                break
            
            self.frame_count += 1
            
            if self.should_update_analysis():
                self.last_analysis = self.analyze_frame(frame)
                self.last_update_time = time.time()
            
            if self.last_analysis:
                frame = self.draw_results(frame, self.last_analysis)
            
            cv2.imshow('Face Analysis - DeepFace', frame)
            
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break
    
    def release(self):
        """
        Освобождение ресурсов
        """
        if self.cap:
            self.cap.release()
        cv2.destroyAllWindows()


class FaceAnalysisApp:
    def __init__(self):
        self.analyzer = None
        
    def run(self):
        """
        апуск приложения
        """
        try:
            self.analyzer = FaceAnalyzer(camera_index=0, frame_skip=5)
            self.analyzer.initialize_camera()
            self.analyzer.run()
        except KeyboardInterrupt:
            print("\nАнализ прерван пользователем")
        except Exception as e:
            print(f"Ошибка: {e}")
        finally:
            if self.analyzer:
                self.analyzer.release()

if __name__ == "__main__":
    app = FaceAnalysisApp()
    app.run()